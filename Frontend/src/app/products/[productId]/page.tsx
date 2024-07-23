import { getData, IProduct } from "@/api";
import Image from "next/image";

export default async function ProductPage({
  params,
}: {
  params: { productId: string };
}) {
  const product = await getData<IProduct>(`/product/${params.productId}`);
  return (
    <div className="container mx-auto px-4 py-8">
      <div className="flex flex-col md:flex-row gap-8">
        <div className="md:w-1/2">
          <Image
            src={`/${product.imageUrl}`}
            alt={product.title}
            width={500}
            height={500}
            className="w-full h-auto object-cover rounded-lg shadow-lg"
          />
        </div>
        <div className="md:w-1/2">
          <h1 className="text-3xl font-bold mb-4">{product.title}</h1>
          <p className="text-xl font-semibold mb-2">
            {product.price.toLocaleString()}원
          </p>
          <p className="text-gray-600 mb-4">
            저자: {product.author} | 출판사: {product.publisher}
          </p>
          <p className="text-gray-600 mb-4">
            출판일: {product.publishDate.toString()}
          </p>
          <div className="border-t border-gray-200 pt-4 mt-4">
            <h2 className="text-xl font-semibold mb-2">책 소개</h2>
            <p className="text-gray-700 whitespace-pre-line">
              {product.contentsInfo}
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
