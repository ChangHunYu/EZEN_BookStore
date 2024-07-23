"use client";

import React, { useState, useEffect, useRef, useCallback } from "react";
import { IProduct, getData } from "@/api";
import { useParams } from "next/navigation";
import SortButton from "@/components/sortButton";
import ProductList from "@/components/productList";

type SortOption = "최신순" | "판매량순";

interface ProductResponse {
  totalPage: number;
  totalCount: number;
  pageNumber: number;
  pageSize: number;
  items: IProduct[];
}

interface ProductPageProps {
  initialData: ProductResponse;
}

const ProductPage: React.FC<ProductPageProps> = ({ initialData }) => {
  const params = useParams();
  const mainCategoryId = params.maincategoryId as string;
  const subCategoryId = params.subcategoryId as string;

  const [selectedOption, setSelectedOption] = useState<SortOption>("최신순");
  const [products, setProducts] = useState<IProduct[]>(initialData.items);
  const [page, setPage] = useState(1);
  const [loading, setLoading] = useState(false);
  const [hasMore, setHasMore] = useState(
    initialData.pageNumber < initialData.totalPage
  );

  const isInitialMount = useRef(true);

  const observer = useRef<IntersectionObserver | null>(null);
  const lastProductElementRef = useCallback(
    (node: HTMLDivElement | null) => {
      if (loading) return;
      if (observer.current) observer.current.disconnect();
      observer.current = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting && hasMore) {
          setPage((prevPage) => prevPage + 1);
        }
      });
      if (node) observer.current.observe(node);
    },
    [loading, hasMore]
  );

  useEffect(() => {
    const fetchProducts = async () => {
      if (isInitialMount.current) {
        isInitialMount.current = false;
        return;
      }

      setLoading(true);
      const sortParam = selectedOption === "최신순" ? "RECENT" : "POPULAR";
      let url = `/product?sort=${sortParam}&pageNumber=${page}&pageSize=10`;
      if (subCategoryId !== undefined) {
        url += `&subCategoryId=${subCategoryId}`;
      } else if (mainCategoryId !== undefined) {
        url += `&mainCategoryId=${mainCategoryId}`;
      }

      try {
        const data = await getData<ProductResponse>(url);
        setProducts((prevProducts) =>
          page === 1 ? data.items : [...prevProducts, ...data.items]
        );
        setHasMore(data.pageNumber < data.totalPage);
      } catch (error) {
        console.error("상품을 가져오는 데 실패했습니다:", error);
      }
      setLoading(false);
    };

    fetchProducts();
  }, [selectedOption, page, mainCategoryId, subCategoryId]);

  const handleOptionChange = (option: SortOption) => {
    setSelectedOption(option);
    setProducts([]);
    setPage(1);
    setHasMore(true);
  };

  return (
    <div>
      <SortButton
        selectedOption={selectedOption}
        onOptionChange={handleOptionChange}
      />
      <ProductList
        products={products}
        lastProductElementRef={lastProductElementRef}
      />
      {loading && <div className="text-center py-4">로딩 중...</div>}
    </div>
  );
};

export default ProductPage;
