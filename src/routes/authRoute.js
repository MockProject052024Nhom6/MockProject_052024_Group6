import express from "express";
import { login, signUp, tokenRef, logout } from "../controllers/authController.js";
const authRoute = express.Router();

authRoute.post("/login", login);

authRoute.post("/signup", signUp);

authRoute.post("/token-ref", tokenRef);

authRoute.post("/logout", logout);

export default authRoute;