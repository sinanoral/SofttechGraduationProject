import { useMutation } from "react-query";
import { BASE_URL, URL } from "../../../api/constants";
import { HttpVerb, getToken, SuccessResponse, ErrorResponse } from "../common";
import { Product } from "./product";

interface CreateProduct {
  name: string;
  categoryId: number;
  price: number;
}

async function createProduct(requestBody: CreateProduct) {
  const requestOptions = {
    method: HttpVerb.POST,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${getToken()}`,
    },
    body: JSON.stringify(requestBody),
  };

  return await fetch(BASE_URL + URL.PRODUCTS, requestOptions).then((res) =>
    res.json()
  );
}

export const useCreateProduct = (requestBody: CreateProduct) => {
  return useMutation<SuccessResponse<Product>, ErrorResponse>(
    "createProduct",
    () => createProduct(requestBody)
  );
};
