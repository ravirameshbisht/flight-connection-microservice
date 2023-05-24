import { useState, useEffect } from 'react';

export const useList = (url) => {
  const [list, setList] = useState([]);
  const [api] = useState(url);
  const [deleted, setDeleted] = useState(false);

  const handleDelete = async (id)=> {
    console.log('deleted', id);
    const response = await fetch(`${url}/${id}`, {
      method: 'DELETE'
    }).then(response => {
      if(response.status === 204){
        console.log(`Airport with id ${id} deleted successfully.`);
        setDeleted(true);
      } else {
        console.error(`Failed to delete airport with id ${id}.`);
      }
    }).catch( error => {
      console.error(`Error occurred while deleting airport with id ${id}.`, error);
    });
}

  useEffect(() => {
    const fetchList = async () => {
      const response = await fetch(url);
      const data = await response.json();
      setList(data);
    };

    fetchList();
    setDeleted(false);
  }, [deleted]);

  return [list, handleDelete];
};
