import './Footer.css';
import AllsurplusLogo from '../../assets/images/allsurplus-logo.png';
import LiquidationLogo from '../../assets/images/liquidation-logo.png';
import Bid4assetsLogo from '../../assets/images/bid4assets-logo.png';
import MachinioLogo from '../../assets/images/machinio-logo.png';
import GovdealsLogo from '../../assets/images/govdeals-logo.png';
import SecondipityLogo from '../../assets/images/secondipity-logo.png';

const Footer = () => {
    return (
        <div id='Footer' className='footer'>
            <div className='footer-content'>
                <div className='content-left'>
                    <div className='content-up'>
                        <div className='left'>
                            <p>Get to Know Us</p>
                            <ul>
                                <li>
                                    Careers
                                </li>
                                <li>
                                    About GovDeals
                                </li>
                                <li>
                                    About Liquidity Services
                                </li>
                                <li>
                                    In The News
                                </li>
                                <li>
                                    Blog
                                </li>
                            </ul>
                        </div>
                        <div className='right'>
                            <p>Sell With Us</p>
                            <ul>
                                <li>
                                    How To Sell
                                </li>
                                <li>
                                    Become A Seller
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div className='content-down'>
                        <div className='left'>
                            <p>Buy From Us</p>
                            <ul>
                                <li>
                                    How To Buy
                                </li>
                                <li>
                                    Why Buy
                                </li>
                                <li>
                                    Store Directory
                                </li>
                            </ul>
                        </div>
                        <div className='right'>
                            <p>Need Hepl?</p>
                            <ul>
                                <li>
                                    Advanced Search
                                </li>
                                <li>
                                    Contact Us
                                </li>
                                <li>
                                    VPAT (PDF)
                                </li>
                                <li>
                                    Financing
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div className='content-right'>
                    <div className='content-up'>
                        <p>Liquidity Services Brands</p>
                        <div className='list-logo'>
                            <div className='logo-item'>
                                <div className='left'>
                                    <img src={AllsurplusLogo} alt='Surplus Aggregator Logo'/>
                                    <span>Surplus Aggregator</span>
                                </div>
                                <div className='right'>
                                    <img src={LiquidationLogo} alt='Retail Surplus'/>
                                    <span>Retail Surplus</span>
                                </div>
                            </div>
                            <div className='logo-item'>
                                <div className='left'>
                                    <img src={Bid4assetsLogo} alt='Property Surplus'/>
                                    <span>Property Surplus</span>
                                </div>
                                <div className='right'>
                                    <img src={MachinioLogo} alt=''/>
                                    <span>Machinery Surplus</span>
                                </div>
                            </div>
                            <div className='logo-item'>
                                <div className='left'>
                                    <img src={GovdealsLogo} alt='Government Surplus'/>
                                    <span>Government Surplus</span>
                                </div>
                                <div className='right'>
                                    <img src={SecondipityLogo} alt='Consumer Surplus'/>
                                    <span>Consumer Surplus</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className='content-down'>
                        <div className='content-media'>
                            <div className='left'>
                                <p>Subscribe</p>
                                <span>Manage Preferences</span>
                            </div>
                            <div className='right'>
                                <p>Follow Us</p>
                                <ul>
                                    <li><i class='bx bxl-facebook'></i></li>
                                    <li><i class='bx bxl-linkedin' ></i></li>
                                    <li><i class='bx bxl-twitter' ></i></li>
                                    <li><i class='bx bxl-youtube' ></i></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className='footer-copyright'>
                <div className='copyright-content'>
                    &copy; 7/2024 MockProject, Inc. All rights reserved.
                </div>
                <div className='footer-nav'>
                    <div>
                        <ul>
                            <li>
                                Site Map
                            </li>
                            <li>
                                Privacy Policy
                            </li>
                            <li>
                                User Agreement
                            </li>
                            <li>
                                Manage Cookies
                            </li>
                        </ul>
                    </div>
                    <div>
                        Language
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Footer;