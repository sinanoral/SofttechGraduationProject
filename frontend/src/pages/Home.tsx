import {
  Table,
  TableCaption,
  Tbody,
  Td,
  Th,
  Thead,
  Tr,
} from "@chakra-ui/react";
import { api } from "../hooks";
import { Product } from "../hooks/api/products/product";

export default function Home() {
  const { isLoading, isError, data: products, error } = api.useGetAllProducts();

  if (isLoading) {
    return <span>Loading...</span>;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Table variant="simple">
      <TableCaption>Products</TableCaption>
      <Thead>
        <Tr>
          <Th>Name</Th>
          <Th>Category</Th>
          <Th isNumeric>Price</Th>
          <Th isNumeric>Vat amount</Th>
          <Th isNumeric>Vat inclusive price</Th>
        </Tr>
      </Thead>
      <Tbody>
        {products?.data.map((product: Product) => (
          <Tr>
            <Td>{product.name}</Td>
            <Th>{product.categoryId}</Th>
            <Th isNumeric>{product.price}</Th>
            <Th isNumeric>{product.vatAmount}</Th>
            <Th isNumeric>{product.vatInclusivePrice}</Th>
          </Tr>
        ))}
      </Tbody>
    </Table>
  );
}
