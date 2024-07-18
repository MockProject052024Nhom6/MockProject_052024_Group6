import express from "express";
import {getProfile, updateProfile} from "../controllers/userController.js";
const userRoute = express.Router();

userRoute.get("/:id", getProfile);
userRoute.put("/:id", updateProfile);
export default userRoute;