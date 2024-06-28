// class Car {
//   constructor({
//     id,
//     marca,
//     rating,
//     marca,
//     dateOfRelease,
//     isInReadlist,
//   }) {
//     this.id = id;
//     this.marca = marca;
//     this.rating = rating;
//     this.marca = marca;
//     this.dateOfRelease = dateOfRelease;
//     this.isInReadlist = isInReadlist;
//   }
// }

// const cars = [];
// s1 = new Car({
//   id: 1,
//   model: "Bon Jovi",
//   rating: 4.34,
//   marca: "It's my life",
//   dateOfRelease: new Date(2010, 3, 12),
//   isInReadlist: false,
// });
// s2 = new Car({
//   id: 2,
//   model: "Imagine Dragons",
//   rating: 5.0,
//   marca: "On top of the world",
//   dateOfRelease: new Date(2009, 4, 1),
//   isInReadlist: false,
// });
// s3 = new Car({
//   id: 3,
//   model: "Linkin Park",
//   rating: 4.29,
//   marca: "New divide",
//   dateOfRelease: new Date(2014, 5, 20),
//   isInReadlist: false,
// });
// s4 = new Car({
//   id: 4,
//   model: "Queen (feat. David Bowie)",
//   rating: 5.1,
//   marca: "Under Pressure",
//   dateOfRelease: new Date(2008, 7, 15),
//   isInReadlist: true,
// });

// cars.push(s1);
// cars.push(s2);
// cars.push(s3);
// cars.push(s4);

// // let lastUpdated = cars[cars.length - 1].date;
// let lastId = cars[cars.length - 1].id;
// // const pageSize = 10;

// const broadcast = (data) =>
//   wss.clients.forEach((client) => {
//     if (client.readyState === WebSocket.OPEN) {
//       client.send(JSON.stringify(data));
//     }
//   });

// const router = new Router();

// router.get("/cars", (ctx) => {
//   console.log(cars);
//   ctx.response.body = cars;
//   ctx.response.status = 200;
// });

// // router.get("/item/:id", async (ctx) => {
// //   const itemId = ctx.request.params.id;
// //   const item = cars.find((item) => itemId === item.id);
// //   if (item) {
// //     ctx.response.body = item;
// //     ctx.response.status = 200; // ok
// //   } else {
// //     ctx.response.body = { message: `item with id ${itemId} not found` };
// //     ctx.response.status = 404; // NOT FOUND (if you know the resource was deleted, then return 410 GONE)
// //   }
// // });

// const createCar = async (ctx) => {
//   const car = ctx.request.body;
//   if (!car.marca || !car.model) {
//     // validation
//     ctx.response.body = { message: "Title or model is missing" };
//     ctx.response.status = 400; //  BAD REQUEST
//     return;
//   }
//   if (!car.rating) {
//     // validation
//     ctx.response.body = { message: "Rating is not a number!" };
//     ctx.response.status = 400; //  BAD REQUEST
//     return;
//   }
//   car.id = lastId + 1;
//   lastId = car.id;
//   //car.isInReadlist = false;
//   cars.push(car);
//   ctx.response.body = car;
//   ctx.response.status = 201; // CREATED
//   console.log(car);
//   broadcast({
//     event: "created",
//     payload: { updatedCar: car },
//   });
// };

// router.post("/car", async (ctx) => {
//   await createCar(ctx);
// });

// router.put("/car/:id", async (ctx) => {
//   const id = parseInt(ctx.params.id);
//   const car = ctx.request.body;
//   const itemId = car.id;
//   console.log(car);
//   if (itemId && id !== car.id) {
//     ctx.response.body = { message: `Param id and body id should be the same` };
//     ctx.response.status = 400; // BAD REQUEST
//     return;
//   }
//   if (!car.rating) {
//     // validation
//     ctx.response.body = { message: "Rating is not a number!" };
//     ctx.response.status = 400; //  BAD REQUEST
//     return;
//   }

//   const index = cars.findIndex((item) => item.id === id);
//   if (index === -1) {
//     ctx.response.body = { issue: [{ error: `item with id ${id} not found` }] };
//     ctx.response.status = 400; // BAD REQUEST
//     return;
//   }
//   const itemVersion = parseInt(ctx.request.get("ETag")) || car.version;
//   if (itemVersion < cars[index].version) {
//     ctx.response.body = { issue: [{ error: `Version conflict` }] };
//     ctx.response.status = 409; // CONFLICT
//     return;
//   }
//   car.version++;
//   cars[index] = car;
//   ctx.response.body = car;
//   ctx.response.status = 200; // OK
//   broadcast({
//     event: "updated",
//     payload: { successMessage: "Updated car!", updatedCar: car },
//   });
// });

// router.del("/car/:id", (ctx) => {
//   const id = parseInt(ctx.params.id);
//   console.log(id);
//   const index = cars.findIndex((item) => id === item.id);
//   console.log(index);
//   if (index !== -1) {
//     const item = cars[index];
//     cars.splice(index, 1);
//     lastUpdated = new Date();
//     console.log(item);
//     broadcast({ event: "deleted", payload: { updatedCar: item } });
//   }
//   ctx.response.status = 204; // no content
// });

// // setInterval(() => {
// //   lastUpdated = new Date();
// //   lastId = `${parseInt(lastId) + 1}`;
// //   const item = new Car({
// //     id: lastId,
// //     text: `item ${lastId}`,
// //     date: lastUpdated,
// //     version: 1,
// //   });
// //   cars.push(item);
// //   console.log(`New item: ${item.text}`);
// //   broadcast({ event: "created", payload: { item } });
// // }, 5000);

// app.use(router.routes());
// app.use(router.allowedMethods());

// server.listen(3000);
import http from "http";
import Koa from "koa";
import WebSocket from "ws";
import Router from "koa-router";
import bodyParser from "koa-bodyparser";
import jwt from "koa-jwt";
import cors from "@koa/cors";
import { jwtConfig, timingLogger, exceptionHandler } from "./utils.js";
import { initWss } from "./wss.js";
import { itemRouter } from "./item.js";
import { authRouter } from "./auth.js";

const app = new Koa();
const server = http.createServer(app.callback());
const wss = new WebSocket.Server({ server });
initWss(wss);

app.use(cors());
app.use(timingLogger);
app.use(exceptionHandler);
app.use(bodyParser());

const prefix = "/api";

// public
const publicApiRouter = new Router({ prefix });
publicApiRouter.use("/auth", authRouter.routes());
app.use(publicApiRouter.routes()).use(publicApiRouter.allowedMethods());

app.use(jwt(jwtConfig));

// protected
const protectedApiRouter = new Router({ prefix });
protectedApiRouter.use("/item", itemRouter.routes());
app.use(protectedApiRouter.routes()).use(protectedApiRouter.allowedMethods());

server.listen(3000);
console.log("started on port 3000");
