import React from "react";

type SortOption = "최신순" | "판매량순";

interface SortButtonProps {
  selectedOption: SortOption;
  onOptionChange: (option: SortOption) => void;
}

const SortButton: React.FC<SortButtonProps> = ({
  selectedOption,
  onOptionChange,
}) => {
  return (
    <div className="flex justify-end mb-1">
      <select
        value={selectedOption}
        onChange={(e) => onOptionChange(e.target.value as SortOption)}
        className="appearance-none bg-white border border-gray-300 text-gray-700 py-2 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
      >
        <option value="최신순">최신순</option>
        <option value="판매량순">판매량순</option>
      </select>
    </div>
  );
};

export default SortButton;
