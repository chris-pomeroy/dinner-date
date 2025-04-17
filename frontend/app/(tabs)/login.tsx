import {SafeAreaView, StyleSheet, TextInput, TouchableOpacity, Text} from 'react-native';
import {useState} from "react";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {login} from "@/api/requests";

export default function LoginScreen() {

    const {mutate: postLogin} = useMutation({
        mutationFn: login
    })

    const queryClient = useQueryClient()

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

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
                placeholder="Email"
            />
            <TextInput
                secureTextEntry={true}
                returnKeyType='done'
                style={styles.input}
                value={password}
                onChangeText={setPassword}
                placeholder="Password"
            />
            <TouchableOpacity
                onPress={() => {
                    postLogin({email, password})
                    queryClient.refetchQueries()
                }}
                style={{
                    height: 50,
                    width: "80%",
                    backgroundColor: "lightblue",
                    alignItems: "center",
                    justifyContent: "center",
                    borderRadius: 10,
                }}>
                <Text style={{
                    fontWeight: "bold"
                }}>Sign in</Text>
            </TouchableOpacity>
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
