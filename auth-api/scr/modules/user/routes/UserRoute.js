import { Router } from "express";
import UserController from "../controller/UserController.js";
import checkToken from "../../../config/auth/checkToken.js";

const router = new Router();

router.post('/user-api/auth', UserController.getAccessToken);

router.use(checkToken);

router.get('/user-api/email/:email', UserController.findByEmail);


export default router;