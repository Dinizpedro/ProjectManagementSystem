import React from 'react';
import not_found from '../assets/not_found.jpg';

function NotFound() {
    return (
        <div>
            <img src={not_found} alt="Not Found Image"/>
            <p style={{textAlign: "center"}}>
            </p>
        </div>
    )
}

export default NotFound;
