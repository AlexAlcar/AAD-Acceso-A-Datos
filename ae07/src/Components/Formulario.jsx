import React, { useState, useEffect } from "react";
import { Collapse, Row, Col, Button, notification, Tag, Card, Image } from "antd";
import { DownloadOutlined } from '@ant-design/icons';

const Formulario = () => {
  const [listaProvincias, setListaProvincias] = useState(null);
  const [codProv, setCodProv] = useState("");
  const [provincia, setProvincia] = useState("");
  const [prevision, setPrevision] = useState(null);
  const [dis, setDis] = useState(true);
  const { Panel } = Collapse;

  const getProvincias = () => {
    /**
     * Método getProvincias
     * Sin parámetros de entrada/salida
     * Descripción: consulta la API para descargar una lista de provincias y añadirlas a un hook de estado. 
     * Posteriormente recorreremos ese hook para llenar un Select.
     */
    fetch(`https://www.el-tiempo.net/api/json/v2/provincias`)
      .then((response) => response.json())
      .then((result) => {
        setListaProvincias(result.provincias);
        setCodProv(result.provincias[0].CODPROV);
      })
      .catch((err) => notification["error"]({ message: `Fallo: ${err.message}` }));
  };

  const buscar = async () => {
    /**
   * Método buscar
   * Sin parámetros de entrada/salida
   * Descripción: Usamos el hook de código de provincia para hacer una llamada a la api que nos devuelve la previsión,
   * los valores recibidos se asignan a un Hook de estado.
   */
    await fetch(`https://www.el-tiempo.net/api/json/v2/provincias/${codProv}`)
      .then((response) => response.json())
      .then((res) => { setPrevision(res); console.log(res) })
      .catch((err) =>
        notification["error"]({ message: `Fallo: ${err.message}` })
      );
    //activamos botón guardar una vez hemos hecho la búsqueda
    setDis(false);
  };

  const guardarInfo = () => {
    /**
     * Método guardarInfo
     * Sin parámetros de entrada/salida
     * Descripción: Hacemos una llamada POST al script php alojado en mi xampp local y le pasamos los parámetros que debe insertar en la BBDD
     */

    let formdata = new FormData();
    formdata.append("provincia", prevision.provincia.NOMBRE_PROVINCIA);
    formdata.append("fecha", new Date().toLocaleDateString());
    formdata.append("hoy", prevision.today.p);
    formdata.append("manyana", prevision.tomorrow.p);

    let requestOptions = {
      method: 'POST',
      body: formdata,
      redirect: 'follow'
    };

    fetch("http://localhost/api/script.php", requestOptions)
      .then(response => response.text())
      .then(result => alert("Datos enviados correctamente"))
      .catch(error => console.log('error', error));
  }

  useEffect(() => {
    getProvincias();
  }, [dis]);

  return (
    <>
      <Row gutter={[16, 16]}>
        <Col span={24}>
          {listaProvincias ? (
            <select onChange={(e) => { setCodProv(e.target.value); setProvincia(e.target.name) }}>
              {listaProvincias.map((p) => (
                <option value={p.CODPROV}>{p.NOMBRE_PROVINCIA}</option>
              ))}
            </select>
          ) : null}

        </Col>
      </Row>
      <br />
      <Button onClick={buscar}>Buscar</Button> &nbsp;
      <Button onClick={guardarInfo} type="primary" disabled={dis ? true : false} icon={<DownloadOutlined />}>Guardar Resultados</Button>
      <p>
        {prevision ? (
          <Collapse defaultActiveKey={['1']}>
            <Panel header="Información" key="1">
              <h2>{prevision.metadescripcion}</h2>
            </Panel>
            <Panel header="Previsión para HOY" key="hoy">
              <p>{prevision.today.p}</p>
            </Panel>
            <Panel header="Previsión para MAÑANA" key="mañana">
              <p>{prevision.tomorrow.p}</p>
            </Panel>
          </Collapse>
        ) : null}
      </p>
    </>
  );
};

export default Formulario;