import { useEffect, useState } from 'react';
import './App.css';
import { DataGrid, GridColDef } from '@mui/x-data-grid';

const App = () => {
  const [loading, setLoading] = useState(true);
  const [respData, setRespData] = useState([]);

  useEffect(() => {
    getAllData();
  }, []);

  const getAllData = () => {
    setLoading(true);
    fetch("http://localhost:8080/wallet/details").then((response) => response.text())
    .then((result) => {      
      let json = JSON.parse(result);
      setRespData(json);
      setLoading(false);
    }).catch((err: any) => {
      console.log(err);
      setLoading(false);
    });    
  }
    
  return <div style={{display: "flex",justifyContent: "center"}}>
    <table style={{border: "1px solid"}}>
        <tr style={{border: "1px solid"}}>
          <th style={{border: "1px solid"}}>ID</th>
          <th style={{border: "1px solid"}}>Name</th>
          <th style={{border: "1px solid"}}>Amount</th>
          <th style={{border: "1px solid"}}>Actions</th>
        </tr>
        {respData.map((val, key) => {
          return (
            <tr key={key}>
              <td style={{border: "1px solid"}}>{val["id"]}</td>
              <td style={{border: "1px solid"}}>{val["name"]}</td>
              <td style={{border: "1px solid"}}>{val["amount"]}</td>
              <td style={{border: "1px solid"}}><a href = "#">add amount</a> | <a href = "#">withdraw amount</a></td>              
            </tr>
          )
        })}
      </table>
  </div>
};

export default App;
