import {SafeAreaView, StyleSheet, Text, TextInput, View} from 'react-native';
import {useState} from "react";
import Button from "@/components/Button";
import {useRegisterMutation} from "@/hooks/mutations/useRegisterMutation";

type Props = {
    hideRegisterScreen: () => void;
}

export default function RegisterScreen({hideRegisterScreen}: Props) {

    const [firstName, setFirstName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const {mutate: postRegister, isError, isPending} = useRegisterMutation();

    return (
        <SafeAreaView style={{
            alignItems: "center",
            justifyContent: "center",
            height: "100%"
        }}>
            <TextInput
                style={styles.input}
                value={firstName}
                onChangeText={setFirstName}
                placeholder="First name"
                autoComplete="given-name"
                textContentType="name"
            />
            <TextInput
                style={styles.input}
                value={email}
                onChangeText={setEmail}
                placeholder="Email address"
                keyboardType="email-address"
                autoCapitalize="none"
                autoComplete="email"
                textContentType="emailAddress"
            />
            <TextInput
                secureTextEntry={true}
                returnKeyType='done'
                style={styles.input}
                value={password}
                onChangeText={setPassword}
                placeholder="Password"
            />
            <Button
                onPress={() => postRegister({firstName, email, password})}
                text="Register"
                isError={isError}
                isLoading={isPending}
            />
            <View style={{
                marginTop: 30,
                flexDirection: "row"
            }}>
                <Text>{"Already have an account? "}</Text>
                <Text
                    onPress={hideRegisterScreen}
                    style={{
                        textDecorationLine: "underline"
                    }}>
                    Sign in
                </Text>
            </View>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    input: {
        height: 50,
        width: "80%",
        backgroundColor: "white",
        borderWidth: 1,
        borderRadius: 5,
        borderColor: "lightgrey",
        padding: 10,
        marginBottom: 10,
    }
})
