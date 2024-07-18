import { responseData } from "../config/response.js";

import initModels from "../models/init-models.js";
import sequelize from "../models/connect.js";
import { Sequelize } from "sequelize";

let model = initModels(sequelize);

import {
  checkRefToken,
  checkToken,
  createRefToken,
  createToken,
  decodeToken,
} from "../config/jwt.js";

let Op = Sequelize.Op;

export const login = async (req, res) => {
  // try{
  let { email, pass_word } = req.body;

  // check email and pass_word == table user
  // SELECT * FROM users WHERE email=email AND pass_word= pass_word
  // if(email==email && pass_word==pass_word)
  let checkUser = await model.tbl_user.findOne({
    where: {
      email: email,
    },
  });

  // tồn tại => login thành công
  if (checkUser) {
    if (bcrypt.compareSync(pass_word, checkUser.password)) {
      // miniliseconds
      let key = new Date().getTime();

      let token = createToken({
        id_user: checkUser.id_user,
        key,
      });

      // generate refresh token
      let refToken = createRefToken({
        id_user: checkUser.id_user,
        key,
      });

      // save refresh token into table user

      // UPDATE users SET ... WHERE ...
      await model.tbl_user.update(
        { ...checkUser.dataValues, refresh_token: refToken },
        {
          where: { id_user: checkUser.id_user },
        }
      );

      responseData(res, "Login successfully", token, 200);
    } else {
      responseData(res, "wrong password", "", 400);
    }
  } else {
    // doesnt exit => wrong email or password
    responseData(res, "wrong email", "", 400);
  }
  // }
  // catch{

  // }
};

export const signUp = async (req, res) => {
  try {
    let { first_name, last_name, email, pass_word } = req.body;

    let checkUser = await model.users.findOne({
      where: {
        email,
      },
    });

    // check duplicate email
    if (checkUser) {
      // res.status(400).send("Email đã tồn tại");
      responseData(res, "Email already exists", "", 400);
      return;
    }

    let newData = {
      first_name,
      last_name,
      username: "",
      date_of_birth: "",
      phone: "",
      another_phone: "",
      email,
      status: "",
      gender: "",
      password: bcrypt.hashSync(pass_word, 10),
      country: "",
      city: "",
      avatar: "",
      state: "",
      interest: "",
      credibility: "",
      identification_card: "",
      demand: "",
      status_user: "",
      created_date: "",
      created_by: "",
      modified_by: "",
      refresh_token: "",
    };

    // Create => thêm mới users
    // INSERT INTO VALUES
    await model.users.create(newData);

    responseData(res, "Register successfully", "", 200);
  } catch {
    responseData(res, "Error...", "", 500);
  }
};

export const tokenRef = async (req, res) => {
  try {
    let { token } = req.headers;

    // check token
    let check = checkToken(token);
    if (check != null && check.name != "TokenExpiredError") {
      // token không hợp lệ
      res.status(401).send(check.name);
      return;
    }

    // {data: { user_id: }}
    let accessToken = decodeToken(token);

    // lấy thông tin user trong database
    let getUser = await model.tbl_user.findOne({
      where: {
        id_user: accessToken.data.id_user,
      },
    });

    // check Ref token
    let checkRef = checkRefToken(getUser.refresh_token);
    if (checkRef != null) {
      // check refresh your token expired or not
      res.status(401).send(check.name);
      return;
    }

    // check code
    let refToken = decodeToken(getUser.refresh_token);
    if (accessToken.data.key != refToken.data.key) {
      res.status(401).send(check.name);
      return;
    }

    // create new access token
    let newToken = createToken({
      id_user: getUser.id_user,
      key: refToken.data.key,
    });

    responseData(res, "", newToken, 200);
  } catch {
    responseData(res, "Lỗi ...", "", 500);
  }
};

export const logout = async (req, res) => {
  try {
    let { token } = req.headers;

    // {data: { user_id: }}
    let accessToken = decodeToken(token);

    // get info of user in database
    let getUser = await model.tbl_user.findOne({
      where: {
        id_user: accessToken.data.id_user,
      },
    });

    await model.users.update(
      { ...getUser.dataValues, refresh_token: "" },
      {
        where: { id_user: getUser.id_user },
      }
    );

    responseData(res, "", newToken, 200);
  } catch {
    responseData(res, "Error ...", "", 500);
  }
};
