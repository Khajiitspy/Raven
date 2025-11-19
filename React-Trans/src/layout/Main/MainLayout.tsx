import MainHeader from "./MainHeader";

export default function MainLayout({ children }) {
    return (
        <div className="min-h-screen bg-neutral-secondary-soft pt-20">
            <MainHeader />
            <main className="max-w-screen-xl mx-auto p-4">
                {children}
            </main>
        </div>
    );
}
