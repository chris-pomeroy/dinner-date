import {SafeAreaView, StyleSheet, Text, TextInput, View} from 'react-native';
import {useState} from "react";
import {useLoginMutation} from "@/hooks/mutations/useLoginMutation";
import Button from "@/components/Button";
import RegisterScreen from "@/components/RegisterScreen";

export default function LoginScreen() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [showRegisterScreen, setShowRegisterScreen] = useState(false);

    const {mutate: postLogin, isError, isPending} = useLoginMutation();

    if (showRegisterScreen) {
        return <RegisterScreen hideRegisterScreen={() => setShowRegisterScreen(false)}/>;
    }

    return (
        <SafeAreaView style={{
            alignItems: "center",
            justifyContent: "center",
            height: "100%"
        }}>
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
                text="Sign in"
                isError={isError}
                isLoading={isPending}
            />
            <View style={{
                marginTop: 30,
                flexDirection: "row"
            }}>
                <Text>{"Don't have an account? "}</Text>
                <Text
                    onPress={() => setShowRegisterScreen(true)}
                    style={{
                        textDecorationLine: "underline",
                    }}>
                    Sign up
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
