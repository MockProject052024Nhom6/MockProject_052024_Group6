import initModels from "../models/init-models.js";
import sequelize from "../models/connect.js";
import { Sequelize } from "sequelize";

import { responseData } from "../config/response.js";

let model = initModels(sequelize)
let Op = Sequelize.Op;

export const getProfile = async (req, res) => {
    try{
        let { id } = req.params;
        let data = await model.tbl_user.findOne({
            where: {
                id_user: id,
            },
          });

          responseData(res, "success", data, 200);
    }
    catch{
        responseData(res, "Error...", "", 500);

    }
};

export const updateProfile = async (req, res) => {
    try{
        let { id } = req.params;
        let {first_name, last_name} = req.body;
        let data = await model.tbl_user.findOne({
            where:{
                id_user: id,
            },
        });
        data.first_name = first_name;
        data.last_name = last_name;
        await model.tbl_user.update(data.dataValues,{
            where:{
                id_user: id,
            }
        });

        responseData(res, "success", data, 200);
    }
    catch{
        responseData(res, "Error...", "", 500);

    }
};
