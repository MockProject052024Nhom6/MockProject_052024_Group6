import express from "express";
import {getAuctions, addAuction, editAuction} from "../controllers/auctionController.js"

const auctionRoute = express.Router();

auctionRoute.get("/get-auction", getAuctions);
auctionRoute.post("/add-auction", addAuction);
auctionRoute.put("/edit-auction", editAuction);

export default auctionRoute;