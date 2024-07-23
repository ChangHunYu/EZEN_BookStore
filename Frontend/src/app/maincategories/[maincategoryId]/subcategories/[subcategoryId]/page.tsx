import { ISubCategory, IMainCategory, getData } from "@/api";
import React, { Suspense } from "react";
import Category from "@/components/category";
import ProductPageWrapper from "@/components/ProductPageWrapper";
export default async function CategoryPage({
  params,
}: {
  params: { maincategoryId: string; subcategoryId: string };
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
            isActive={s.subCategoryId === Number(params.subcategoryId)}
          />
        ))}
      </div>
      <div className="border-b mb-5" />

      <div>
        <Suspense fallback={<div>Loading products...</div>}>
          <ProductPageWrapper subcategoryId={params.subcategoryId} />
        </Suspense>
      </div>
    </div>
  );
}
