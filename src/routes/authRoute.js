import express from "express";
import { login } from "../controllers/authController.js";
const authRoute = express.Router();

authRoute.post("/login", login);

export default authRoute;