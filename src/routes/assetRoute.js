import express from "express";
import{getCategoryOfAsset, addCategoryOfAsset, removeAsset} from '../controllers/assetController.js';
const assetRoute = express.Router();

assetRoute.get("/get-category-asset", getCategoryOfAsset);
assetRoute.post("/add", addCategoryOfAsset);
assetRoute.put("/delete", removeAsset);

export default assetRoute;