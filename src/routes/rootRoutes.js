import express from 'express';
import authRoute from './authRoute.js';
import userRoute from './userRoute.js';
import assetRoute from './assetRoute.js';
import auctionRoute from './auctionRoute.js';

const rootRoute = express.Router();

rootRoute.use("/auth", authRoute);

rootRoute.use("/users",userRoute);

rootRoute.use("/assets", assetRoute);

rootRoute.use("/auctions", auctionRoute);

export default rootRoute;