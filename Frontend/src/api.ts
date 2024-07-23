import fetch from "node-fetch";

export interface IMainCategory {
  mainCategoryId: number;
  name: string;
}

export interface ISubCategory {
  subCategoryId: number;
  name: string;
}

export interface IProduct {
  productId: number;
  title: string;
  price: number;
  publishDate: string;
  author: string;
  publisher: string;
  imageUrl: string;
  contentsInfo?: string;
}

export interface IProductResponse {
  totalPage: number;
  totalCount: number;
  pageNumber: number;
  pageSize: number;
  items: IProduct[];
}

export async function getData<T>(path: string): Promise<T> {
  const host = "http://localhost:8080";
  const response = await fetch(`${host}${path}`);
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return await response.json();
}
