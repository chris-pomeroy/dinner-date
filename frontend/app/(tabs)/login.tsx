import {SafeAreaView, StyleSheet, TextInput} from 'react-native';
import {useState} from "react";

export default function LoginScreen() {


    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    return (
        <SafeAreaView style={{
            alignItems: "center",
            justifyContent: "center",
        }}>
            <TextInput
                style={styles.input}
                value={email}
                onChangeText={setEmail}
                placeholder="Email"
            />
            <TextInput
                style={styles.input}
                value={password}
                onChangeText={setPassword}
                placeholder="Password"
            />
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
        padding: 10,
    }
})
