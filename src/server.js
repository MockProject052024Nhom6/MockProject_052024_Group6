import express from "express";
import cors from "cors";
import rootRoute from "./routes/rootRoutes.js";

const app = express();
app.use(express.json());

app.use(cors());

app.use(express.static("."));

app.listen(8888);
app.use(rootRoute);

