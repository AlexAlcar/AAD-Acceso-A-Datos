import logo from './logo.svg';
import './App.css';
import Formulario from './Components/Formulario';
import 'antd/dist/antd.css';
import {Image } from "antd";


function App() {
  return (
    <div className="App">
      <p>Weather API</p>
      <Image width={200}  src="https://v5i.tutiempo.net/mapas/espana/espana.jpg" />
      <br/><br/>
      <Formulario />
    </div>
  );
}

export default App;
