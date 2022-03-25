import {
  Flex,
  IconButton,
  Table,
  TableCaption,
  Tbody,
  Td,
  Th,
  Thead,
  Tr,
} from "@chakra-ui/react";
import { DeleteIcon, EditIcon } from "@chakra-ui/icons";

import CreateProduct from "../components/products/CreateProduct";
import { api } from "../hooks";
import { Product } from "../hooks/api/products/product";

export default function Home() {
  const {
    isLoading,
    isError,
    data: products,
    error,
    refetch,
  } = api.useGetAllProducts();
  const { mutate: deleteProductById } = api.useDeleteProductById();

  if (isLoading) {
    return <span>Loading...</span>;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Flex justify="space-between" w="100%">
      <Flex flexDir={"column"} w="40%">
        <CreateProduct />
      </Flex>
      <Table variant="simple">
        <TableCaption>Products</TableCaption>
        <Thead>
          <Tr>
            <Th>Name</Th>
            <Th>Category</Th>
            <Th isNumeric>Price</Th>
            <Th isNumeric>Vat amount</Th>
            <Th isNumeric>Vat inclusive price</Th>
            <Th isNumeric>Update</Th>
            <Th isNumeric>Delete</Th>
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
              <Th>
                <Flex justify="flex-end">
                  <IconButton
                    colorScheme="orange"
                    aria-label="Update product"
                    icon={<EditIcon />}
                  />
                </Flex>
              </Th>
              <Th>
                <Flex justify="flex-end">
                  <IconButton
                    colorScheme="red"
                    aria-label="Delete product"
                    icon={<DeleteIcon />}
                    onClick={() => {
                      deleteProductById(product.id, {
                        onSuccess: () => {
                          refetch();
                        },
                      });
                    }}
                  />
                </Flex>
              </Th>
            </Tr>
          ))}
        </Tbody>
      </Table>
    </Flex>
  );
}
