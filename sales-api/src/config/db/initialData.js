import Order from "../../modules/sales/model/order";

export async function createInitialData() {
  await Order.collection.drop();
  await Order.create({
    products: [
      {
        productId: 1001,
        quantity: 2,
      },
      {
        productId: 1002,
        quantity: 1,
      },
      {
        productId: 1003,
        quantity: 1,
      },
    ],
    user: {
      id: "sd1s1s1d2fs1d2f1sd2f1s2df12sd1f2",
      name: "User Test",
      email: "usertest@mail.com",
    },
    status: "APPROVED",
    createdAt: new Date(),
    updatedAt: new Date(),
  });

  await Order.create({
    products: [
      {
        productId: 1001,
        quantity: 4,
      },
      {
        productId: 1003,
        quantity: 2,
      },
    ],
    user: {
      id: "sd1s1s1d2fs3d2f2sd2f1s3df23sd2f3",
      name: "User Test 2",
      email: "usertest2@mail.com",
    },
    status: "REJECTED",
    createdAt: new Date(),
    updatedAt: new Date(),
  });
  let initialData = await Order.find();
  console.info(`Initial data was created: ${JSON.stringify(initialData, undefined, 4)}`);
}
