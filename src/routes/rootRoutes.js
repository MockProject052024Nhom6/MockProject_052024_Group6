import express from 'express';
import authRoute from './authRoute.js';

const rootRoute = express.Router();

rootRoute.use("/auth", authRoute);

export default rootRoute;