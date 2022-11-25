import { appColors } from "@/shared/theme";
import { Button, Loading } from "@nextui-org/react";

interface GlobalButtonProps {
  text: string;
  onPress: () => void;
  size?: "xs" | "sm" | "md" | "lg" | "xl";
  icon?: JSX.Element;
  loading?: boolean;
}

export default function GlobalButton({
  text,
  onPress,
  size,
  icon,
  loading,
}: GlobalButtonProps) {
  return (
    <Button
      size={size ?? "md"}
      color={"primary"}
      css={{
        maxW: "$10",
        backgroundColor: appColors.white,
        border: "1px solid #005FF9",
        color: appColors.primary,
        marginTop: "$10",
        padding: "$5",
      }}
      onPress={() => onPress()}
    >
      {!loading && icon}
      {!loading && text}
      {loading && <Loading type="points" color="currentColor" size="sm" />}
    </Button>
  );
}
