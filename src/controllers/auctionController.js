import initModels from "../models/init-models.js";
import sequelize from "../models/connect.js";
import { Sequelize } from "sequelize";

import { responseData } from "../config/response.js";

let model = initModels(sequelize);
let Op = Sequelize.Op;

export const getAuctions = async (req, res) => {
  try {
    let data = await model.tbl_auction.findAll({});
    responseData(res, "successfully", data, 200);
  } catch {
    responseData(res, "Error...", "", 500);
  }
};

export const addAuction = async (req, res) => {
  try {
    let { auction_name, address, number_of_participants, start_time, end_time, type_auction, status_auction} = req.body;
    let data = await model.tbl_auction.create({
        auction_name,
        address,
        number_of_participants,
        start_time,
        end_time,
        type_auction,
        status_auction,
    });
    responseData(res, "successfully", data, 200);
  } catch {
    responseData(res,"Error...", "", 500)
  }
};

export const editAuction = async (req, res) => {
  try {
    let{id} = req.params;
    let { auction_name, address, number_of_participants, start_time, end_time, type_auction, status_auction} = req.body;
    let data = await model.tbl_auction.findOne({
        where:{
            id_auction: id
        }
    });
    data.auction_name = auction_name;
    data.address = address;
    data.number_of_participants = number_of_participants;
    data.start_time = start_time;
    data.end_time = end_time;
    data.type_auction = type_auction;
    data.status_auction = status_auction;

    await model.tbl_auction.update(data.dataValues, {
        where: {
          id_auction: id,
        },
      });

    responseData(res, "successfully", data, 200);

  } catch {
    responseData(res, "Error...", "", 500);

  }
};
