import React from "react";
import { IProduct } from "@/api";
import Product from "@/components/product";

interface ProductListProps {
  products: IProduct[];
  lastProductElementRef: (node: HTMLDivElement | null) => void;
}

const ProductList: React.FC<ProductListProps> = ({ products, lastProductElementRef }) => {
  return (
    <div className="grid grid-cols-2 gap-x-4 gap-y-10 pt-4 pb-12">
      {products.map((p, index) => (
        <div 
          key={p.productId} 
          ref={index === products.length - 1 ? lastProductElementRef : null}
        >
          <Product
            id={p.productId}
            title={p.title}
            price={p.price}
            publishDate={p.publishDate}
            author={p.author}
            publisher={p.publisher}
            imageUrl={p.imageUrl}
          />
        </div>
      ))}
    </div>
  );
};

export default ProductList;