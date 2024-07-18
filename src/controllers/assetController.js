import initModels from "../models/init-models.js";
import sequelize from "../models/connect.js";
import { Sequelize } from "sequelize";

import { responseData } from "../config/response.js";

let model = initModels(sequelize)
let Op = Sequelize.Op;

export const getCategoryOfAsset = async (req, res) => {
    try{
        let data = await model.tbl_category_asset.findAll({});
        responseData(res, "successfully", data, 200);

    }
    catch{

    responseData(res, "Error...", "", 500);

    }
};

export const addCategoryOfAsset = async (req, res) => {
    try{

    }
    catch{

    }
};

export const removeAsset = async (req, res) => {
    try{

    }
    catch{

    }
};