import {SafeAreaView, StyleSheet, TextInput, Text, Pressable} from 'react-native';
import {useState} from "react";
import {useLoginMutation} from "@/hooks/mutations/useLoginMutation";
import Button from "@/components/Button";

type Props = {
    hideRegisterScreen: () => void;
}

export default function RegisterScreen({hideRegisterScreen}: Props) {

    const [firstName, setFirstName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const {mutate: postLogin, isError, isPending} = useLoginMutation();

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
                autoComplete="name-given"
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
                onPress={() => postLogin({email, password})}
                text="Register"
                isError={isError}
                isLoading={isPending}
            />
            <Text style={{
                marginTop: 30,

            }}>
                Already have an account?{" "}
                <Pressable onPress={hideRegisterScreen}>
                    <Text style={{
                        textDecorationLine: "underline"
                    }}>
                        Sign in
                    </Text>
                </Pressable>
            </Text>
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
