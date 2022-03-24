import { useMutation, useQuery } from "react-query";
import { BASE_URL, URL } from "../../../api/constants";
import { HttpVerb, getToken, SuccessResponse, ErrorResponse } from "../common";
import { Product } from "./product";

interface UpdateProduct {
  name: string;
  categoryId: number;
  price: number;
}

async function updateProductById(id: number, requestBody: UpdateProduct) {
  const requestOptions = {
    method: HttpVerb.PUT,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${getToken()}`,
    },
    body: JSON.stringify(requestBody),
  };

  return await fetch(BASE_URL + URL.PRODUCTS + "/" + id, requestOptions).then(
    (res) => res.json()
  );
}

export const useUpdateProductById = (
  id: number,
  requestBody: UpdateProduct
) => {
  return useMutation<SuccessResponse<Product>, ErrorResponse>(
    "updateProductById",
    () => updateProductById(id, requestBody)
  );
};
