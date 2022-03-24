import { useLogin } from "./auth/useLogin";
import { useRegister } from "./auth/useRegister";
import { useGetAllProducts } from "./products/useGetAllProducts";
import { useGetProductById } from "./products/useGetAllProductsByCategoryId";
import { useUpdateProductById } from "./products/useUpdateProductById";

export default {
  useLogin,
  useRegister,
  useGetAllProducts,
  useGetProductById,
  useUpdateProductById,
};
