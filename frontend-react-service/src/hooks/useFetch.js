import React, { useState, useEffect } from 'react';

export function useFetch(uri, formData) {

    const [data, setData] = useState();
    const [error, setError] = useState();
    const [loading, setLoading] = useState(true);
    
    useEffect(() => {
        if (!uri) return;
        fetch(uri, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
        })
        .then(data => data.json())
        .then(setData)
        .then(() => setLoading(false))
        .catch(setError);
    }, [uri]);
    return {
        loading,
        data,
        error
    };
}