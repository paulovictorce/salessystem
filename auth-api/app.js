import express from "express";
import * as db from "./scr/config/db/initialData.js"

const app = express();
const env = process.env;
const PORT = env.PORT || 8080;

db.createInitialData();

app.get('/api/status', (req, res) => {
    return res.status(200).json({
        service: "AUTH-API",
        status: "UP",
        httpStatus: 200
    });
});

app.listen(PORT, () => {
    console.info(`SERVER ON AT PORT ${PORT}`);
});