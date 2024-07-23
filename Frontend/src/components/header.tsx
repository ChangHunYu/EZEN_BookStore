"use client";

import { useRouter } from "next/navigation";
import Link from "next/link";

interface HeaderProps {
  children: React.ReactNode;
  hasBack: boolean;
}

export default function Header({ children, hasBack }: HeaderProps) {
  const router = useRouter();
  return (
    <header>
      <div className="border-b w-full flex items-center justify-between px-5 py-4">
        <div className="aspect-square size-6">
          {hasBack && (
            <button
              onClick={() => router.back()}
              className="bg-white cursor-pointer"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth={1.5}
                stroke="currentColor"
                className="size-5"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M15.75 19.5 8.25 12l7.5-7.5"
                />
              </svg>
            </button>
          )}
        </div>
        <div className="font-bold text-xl">{children}</div>
        <div className="aspect-square size-6">
          <Link href="/" className="bg-white cursor-pointer">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="size-5"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="m2.25 12 8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"
              />
            </svg>
          </Link>
        </div>
      </div>
    </header>
  );
}
