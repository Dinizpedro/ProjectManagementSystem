import * as React from 'react';
import ImageAvatars from "../components/ImageAvatars";

function Home() {
    return (
        <div style={{height: '100%', width: '100%'}}>
        <img style={{width: '40%', height: '40%', resizeMode: 'contain'}}
             src={require('../assets/javalicious.png')}/>
            <ImageAvatars />
        </div>
    )
}


export default Home