import Link from "next/link";

export default function Category({
  name,
  href,
  isActive,
}: {
  name: string;
  href: string;
  isActive?: boolean;
}) {
  return (
    <Link href={href} className="w-full rounded-md hover:bg-stone-100 p-1">
      <div
        className={`w-full flex flex-col justify-center gap-1 items-center ${
          isActive
            ? "bg-gray-100 rounded-md text-black"
            : "bg-white hover:bg-gray-100 rounded-md text-stone-700 "
        }`}
      >
        <div
          className={`text-sm ${isActive ? "text-black" : "text-stone-700"}`}
        >
          {name}
        </div>
      </div>
    </Link>
  );
}
