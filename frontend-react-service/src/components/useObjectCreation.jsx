import { useState } from 'react';
import {useNavigate } from 'react-router-dom';

export const useObjectCreation = (url,redirectUrl) => {
  const [formData, setFormData] = useState({});
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (event) => {
    console.log('event = ', event);
    if (event && event.target) {
      setFormData({ ...formData, [event.target.name]: event.target.value });
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify(formData),
    }).then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      navigate(`${redirectUrl}`, { replace: true });
      //setLoading(false);
    }).catch(error => {
      console.log('there is an error:', error);
    })
    .finally(() => setLoading(false));;
  };

  return [formData, loading, handleChange, handleSubmit];
};
