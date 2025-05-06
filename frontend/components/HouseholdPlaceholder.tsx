import {Text, TouchableOpacity} from "react-native";
import {useInviteMutation} from "@/hooks/mutations/useInviteMutation";

export default function HouseholdPlaceholder() {

    const {mutate: invite} = useInviteMutation()

    return (
        <TouchableOpacity
            onPress={() => invite()}
            style={{
                width: "80%",
                backgroundColor: "white",
                flexDirection: "row",
                alignItems: "center",
                borderColor: "turquoise",
                borderWidth: 5,
                borderRadius: 10,
                marginBottom: 10
            }}>
            <Text style={{
                fontSize: 50,
                margin: 20
            }}>{"+"}</Text>
            <Text style={{
                flex: 1,
                marginLeft: 10,
                fontSize: 20
            }}>{"Send invite"}</Text>
        </TouchableOpacity>
    );
}
