import {Text, View} from "react-native";

type Props = {
    firstName: string;
}

export default function HouseholdMember({firstName}: Props) {

    return (
        <View style={{
            width: "80%",
            backgroundColor: "white",
            flexDirection: "row",
            alignItems: "center",
            borderRadius: 10,
            borderWidth: 5,
            borderColor: "turquoise",
            marginBottom: 10
        }}>
            <Text style={{
                fontSize: 50,
                margin: 20,
            }}>{firstName.at(0)?.toUpperCase()}</Text>
            <Text style={{
                flex: 1,
                marginLeft: 10,
                fontSize: 20,
            }}>{firstName}</Text>
        </View>
    );
}
