import './Header.css';
import Logo from '../../assets/images/logo.png';
import 'boxicons/css/boxicons.min.css';

const Header = () => {
    return (
        <div id='header' className=''>
            <div className="partner">
                <div className='header-partner'>
                    <p className='partner-name'>Liquidity Services Brands</p>
                    <div className='rs-show nav-down'><i className='bx bxs-down-arrow'></i></div>
                </div>
                <div className="list-partner">
                    <ul>
                        <li>ALLSURPLUS</li>
                        <li>GovDeals</li>
                        <li>machinio</li>
                        <li>Liquidation.com</li>
                        <li>bid4assets</li>
                    </ul>
                </div>
            </div>
            <div className='nav'>
                <div className='navbar'>
                    <ul>
                        <li>About Us</li>
                        <li>Buy</li>
                        <li>Sell</li>
                        <li>FAQ</li>
                        <li>Contact Us</li>
                        <li>View Canada</li>
                    </ul>
                </div>
                <div className='navbar'>
                    <ul>
                        <li>Login</li>
                        <li className='avt-user'>User</li>
                    </ul>
                </div>
            </div>
            <div className='search-area'>
                <div className='rs-nav'>
                    <div className='rs-show nav-left icon'>
                        <i className='bx bx-menu-alt-left' ></i>
                    </div>
                    <img src={Logo} alt='Logo' />
                    <div className='rs-show icon btn-cart'>
                        <i className='bx bxs-cart'></i>
                    </div>
                </div>
                <div className='search'>
                    <div className='search-group'>
                        <input type='text' className='input' placeholder='Search Assets' />
                        <i className='bx bx-search search-icon'></i>
                    </div>
                    <span className='search-advanced'>Advanced Search</span>
                </div>
            </div>

        </div>
    )
}

export default Header;