import {BrowserRouter as Router, Route, Switch, Link} from 'react-router-dom';
import './App.css';
import Header from './components/header/Header';
import Footer from './components/footer/Footer';
import Asset from './pages/Assets/Assets';


function App() {
  return (
    <div className="App">
        <Router>
            <Header/>
            <Asset/>
            <Footer/>
        </Router>
    </div>
  );
}

export default App;
