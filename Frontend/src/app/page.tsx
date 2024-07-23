import React, { Suspense } from "react";
import { IMainCategory, getData } from "@/api";
import Category from "@/components/category";
import Header from "@/components/header";
import ProductPageWrapper from "@/components/ProductPageWrapper";

export default async function Home() {
  const categories = await getData<IMainCategory[]>("/maincategory");

  return (
    <div className="h-full w-full flex flex-col relative overflow-hidden">
      <Header hasBack={false}>Ezen 서점</Header>
      <div className="w-full h-full flex flex-col overflow-y-auto py-5 px-5 scrollbar-hide">
        <div>
          <div className="font-semibold text-xl">국내도서</div>
          <div className="grid grid-cols-4 gap-2 py-4">
            {categories.map((c, index) => (
              <Category
                name={c.name}
                href={`/maincategories/${c.mainCategoryId}`}
                key={index}
              />
            ))}
          </div>
        </div>
        <div className="border-b mb-5" />
        <Suspense fallback={<div>Loading products...</div>}>
          <ProductPageWrapper />
        </Suspense>
      </div>
    </div>
  );
}
