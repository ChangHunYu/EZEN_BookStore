import Header from "@/components/header";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="h-full w-full flex flex-col relative overflow-hidden">
      <Header hasBack={true}>Ezen 서점</Header>
      <div className="w-full h-full flex flex-col overflow-y-auto py-5 px-5 scrollbar-hide">
        {children}
      </div>
    </div>
  );
}
