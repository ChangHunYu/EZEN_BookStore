// components/ProductPageWrapper.tsx
import { getData, IProductResponse } from "@/api";
import ProductPage from "@/components/productpage";
interface ProductPageWrapperProps {
  maincategoryId?: string;
  subcategoryId?: string;
}

export default async function ProductPageWrapper({
  maincategoryId,
  subcategoryId,
}: ProductPageWrapperProps) {
  let url = "/product?sort=RECENT&pageNumber=1";

  if (maincategoryId) {
    url += `&mainCategoryId=${maincategoryId}`;
  }

  if (subcategoryId) {
    url += `&subCategoryId=${subcategoryId}`;
  }

  const initialData = await getData<IProductResponse>(url);

  return <ProductPage initialData={initialData} />;
}
