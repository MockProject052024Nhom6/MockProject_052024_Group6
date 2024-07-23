import './Assets.css';

const Asset = () => {
    return (
        <div id='asset' className='asset'>
            <div className='navbar left'>
                <ul>
                    <li>New assets</li>
                    <li>My assets</li>
                    <li>Payments</li>
                    <li>Shipping</li>
                </ul>
            </div>
            <div className='asset-content'>
                <div className='navbar right'>
                    <ul>
                        <li>Home</li>
                        <li>Sell</li>
                        <li>Sell an item</li>
                    </ul>
                </div>
                <div className='asset-info'>
                    <h2>Asset information</h2>
                </div>
                <div className='asset-form'>
                    <form>
                        <div className='form-content'>
                            <div className='form-info left'>
                                <div className='form-group'>
                                    <label for='product-name'>Product title</label>
                                    <input type='text' id='product-name' className='form-control' required />
                                </div>
                                <div className='form-group'>
                                    <label for='product-des'>Description</label>
                                    <textarea id='product-des' className='form-control' required></textarea>
                                </div>
                                <div className='form-group'>
                                    <label className='product-value' for='product-value'>
                                        <span>Estimated value </span>
                                        <i class='bx bxs-dollar-circle'></i>
                                    </label>
                                    <input type='text' id='product-value' className='form-control' required />
                                </div>
                                <div className='form-group'>
                                    <label for='product-type'>Product type</label>
                                    <select className='form-control' id='product-type' name='product-type'>
                                        <option value='Car'>Car</option>
                                        <option value='Smartphone'>Smartphone</option>
                                        <option value='Motorcycle'>Motorcycle</option>
                                    </select>
                                </div>
                                <div className='form-group'>
                                    <label for='product-status'>Status</label>
                                    <select className='form-control' id='product-status' name='product-status'>
                                        <option value='Used'>Used</option>
                                        <option value='Auctioned'>Auctioned</option>
                                    </select>
                                </div>
                                <div className='form-group'>
                                    <label for='auction-form'>Auction form</label>
                                    <select className='form-control' id='auction-form' name='auction-form'>
                                        <option value='Live auction'>Live auction</option>
                                        <option value='Next live auction'>Next live auction</option>
                                    </select>
                                </div>
                            </div>
                            <div className='form-info right'>
                                <p>Images and videos</p>
                                <div className='images-videos'>
                                    <i class='bx bxs-image'></i>
                                    <label>Add from 3 to 12 images and max 1 video</label>
                                    <input type='file' className='file' />
                                </div>
                                <p>Upload assetsment document</p>
                                <div className='form-group'>
                                    <input type='file' id='upload-document' className='upload-document' />
                                    <label for='upload-document'>Upload file</label>
                                </div>
                                <div className='form-group'>
                                    <input type='checkbox' id='check-request' className='check-request' />
                                    <label for='check-request'>Request for assetsment</label>
                                </div>
                            </div>
                        </div>
                        <div className='note'>
                            <span>Note: during holidays, online auctions activities will not be held</span>
                        </div>
                        <div>
                            <button className='btn-save'>Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default Asset;