import express from "express";

import { connect } from "./src/config/db/mongoDbConfig";
import { createInitialData } from "./src/config/db/initialData";

const app = express();
const env = process.env;
const PORT = env.PORT || 8082;

connect();
createInitialData();

app.get("/api/status", async (req, res) => {
  return res.status(200).json({
    service: "SALES-API",
    status: "UP",
    httpStatus: 200,
  });
});

app.listen(PORT, () => {
  console.info(`SERVER ON AT PORT ${PORT}`);
});
