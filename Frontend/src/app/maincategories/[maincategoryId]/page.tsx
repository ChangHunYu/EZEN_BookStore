import { ISubCategory, IMainCategory, getData } from "@/api";
import React, { Suspense } from "react";
import ProductPageWrapper from "@/components/ProductPageWrapper";
import Category from "@/components/category";
import { IProductResponse } from "@/api";

export default async function CategoryPage({
  params,
}: {
  params: { maincategoryId: string };
}) {
  const maincategory = await getData<IMainCategory>(
    `/maincategory/${params.maincategoryId}`
  );

  const subCategories = await getData<ISubCategory[]>(
    `/subcategory?mainCategoryId=${params.maincategoryId}`
  );

  return (
    <div>
      <div className="font-semibold text-xl">{maincategory.name}</div>
      <div className="grid grid-cols-4 gap-2 py-4">
        {subCategories.map((s, index) => (
          <Category
            name={s.name}
            href={`/maincategories/${params.maincategoryId}/subcategories/${s.subCategoryId}`}
            key={index}
          />
        ))}
      </div>
      <div className="border-b mb-5" />
      <Suspense fallback={<div>Loading products...</div>}>
        <ProductPageWrapper maincategoryId={params.maincategoryId}/>
      </Suspense>
    </div>
  );
}
