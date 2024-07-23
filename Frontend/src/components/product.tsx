import Link from "next/link";
import Image from "next/image";

export default function Product({
  id,
  title,
  price,
  publishDate,
  author,
  publisher,
  imageUrl,
}: {
  id: number;
  title: string;
  price: number;
  publishDate: string;
  author: string;
  publisher: string;
  imageUrl: string;
}) {
  const formattedPrice = price.toLocaleString("kr");
  return (
    <Link
      href={`/products/${id}`}
      className="border border-stone-200 rounded-md w-full flex flex-col gap-2"
    >
      <div className="bg-stone-100 rounded-md w-full h-80  relative overflow-hidden">
        <Image
          src={`/${imageUrl}`}
          alt={title}
          fill
          className="object-contain"
        />
      </div>
      <div className="ml-2 mb-1 ">
        <div className="font-sm">{title}</div>
        <div className="font-semibold">{formattedPrice}원</div>
        <div className="text-sm text-stone-600">
          <span>{author}</span>
          <span> | </span>
          <span>{publisher}</span>
        </div>
        <div className="text-sm font-light">{publishDate}</div>
      </div>
    </Link>
  );
}
