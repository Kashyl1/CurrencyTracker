import React, { useState } from 'react';
import './App.css';

function App() {
  // State variables for controlling the view and input values
  const [view, setView] = useState('');
  const [code, setCode] = useState('');
  const [date, setDate] = useState('');
  const [code2, setCode2] = useState('');
  const [topCount, setTopCount] = useState('');
  const [code3, setCode3] = useState('');
  const [topCount1, setTopCount1] = useState('');
  const [exchangeRate1, setExchangeRate1] = useState('');
  const [exchangeRate2, setExchangeRate2] = useState('');
  const [exchangeRate3, setExchangeRate3] = useState('');

  // Styling for input table and cells
  const inputTableStyle = {
    width: '50%',
    border: '1px solid black',
    borderCollapse: 'collapse',
    fontSize: '1.2em'
  };
  const inputCellStyle = {
    padding: '10px',
    textAlign: 'left'
  };

  // Function to handle form submission for exchange rate by date
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch(`http://localhost:8080/a/${code}/${date}`);
      if (!response.ok) {
        throw new Error(`Error ${response.status}: ${await response.text()}`);
      }
      const data = await response.text();
      setExchangeRate1(data);
    } catch (error) {
      setExchangeRate1(error.message);
    }
  };

  // Function to handle form submission for max and min exchange rates
  const handleMaxMinSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch(`http://localhost:8080/a/${code2}/last/${topCount}`);
      if (!response.ok) {
        throw new Error(`Error ${response.status}: ${await response.text()}`);
      }
      const data = await response.text();
      setExchangeRate2(data);
    } catch (error) {
      setExchangeRate2(error.message);
    }
  };

  // Function to handle form submission for major difference between bid and ask
  const handleMajorDifferenceSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch(`http://localhost:8080/c/${code3}/last/${topCount1}`);
      if (!response.ok) {
        throw new Error(`Error ${response.status}: ${await response.text()}`);
      }
      const data = await response.text();
      setExchangeRate3(data);
    } catch (error) {
      setExchangeRate3(error.message);
    }
  };

     return (
       <div className="App">
         <h1>Exchange Rate App</h1>

         {/* Buttons to change the view */}
         <button onClick={() => setView('exchangeRate')}>Get Exchange Rate</button>
         <button onClick={() => setView('maxMin')}>Get Max and Min Exchange Rates</button>
         <button onClick={() => setView('majorDifference')}>Get Major Difference Between Bid and Ask</button>

         {/* Form and display for exchange rate by date */}
         {view === 'exchangeRate' && (
           <>
             <form onSubmit={handleSubmit}>
               <table style={inputTableStyle}>
                 <tbody>
                   <tr>
                     <td style={inputCellStyle}>Currency code:</td>
                     <td><input type="text" value={code} onChange={e => setCode(e.target.value)} /></td>
                   </tr>
                   <tr>
                     <td style={inputCellStyle}>Date:</td>
                     <td><input type="date" value={date} onChange={e => setDate(e.target.value)} /></td>
                   </tr>
                 </tbody>
               </table>
               <input type="submit" value="Get Exchange Rate" />
             </form>
             <div style={{ color: 'red', fontSize: '1.2em' }} dangerouslySetInnerHTML={{ __html: exchangeRate1 }} />
           </>
         )}

      {/* Form and display for max and min exchange rates */}
         {view === 'maxMin' && (
           <>
             <form onSubmit={handleMaxMinSubmit}>
               <table style={inputTableStyle}>
                 <tbody>
                   <tr>
                     <td style={inputCellStyle}>Currency code:</td>
                     <td><input type="text" value={code2} onChange={e => setCode2(e.target.value)} /></td>
                   </tr>
                   <tr>
                     <td style={inputCellStyle}>Top count:</td>
                     <td><input type="number" value={topCount} onChange={e => setTopCount(e.target.value)} /></td>
                   </tr>
                 </tbody>
               </table>
               <input type="submit" value="Get Max and Min Exchange Rates" />
             </form>
             <div style={{ color: 'red', fontSize: '1.2em' }} dangerouslySetInnerHTML={{ __html: exchangeRate2 }} />
           </>
         )}

         {/* Form and display for for max and min exchange rates */}
         {view === 'majorDifference' && (
           <>
             <form onSubmit={handleMajorDifferenceSubmit}>
               <table style={inputTableStyle}>
                 <tbody>
                   <tr>
                     <td style={inputCellStyle}>Currency code:</td>
                     <td><input type="text" value={code3} onChange={e => setCode3(e.target.value)} /></td>
                   </tr>
                   <tr>
                     <td style={inputCellStyle}>Top Count:</td>
                     <td><input type="number" value={topCount1} onChange={e => setTopCount1(e.target.value)} /></td>
                   </tr>
                 </tbody>
               </table>
               <input type="submit" value="Get Major Difference Between Bid and Ask" />
             </form>
             <div style={{ color: 'red', fontSize: '1.2em' }} dangerouslySetInnerHTML={{ __html: exchangeRate3 }} />
           </>
         )}
       </div>
     );
     }

     export default App;